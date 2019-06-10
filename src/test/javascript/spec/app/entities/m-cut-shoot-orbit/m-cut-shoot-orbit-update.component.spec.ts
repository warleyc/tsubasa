/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCutShootOrbitUpdateComponent } from 'app/entities/m-cut-shoot-orbit/m-cut-shoot-orbit-update.component';
import { MCutShootOrbitService } from 'app/entities/m-cut-shoot-orbit/m-cut-shoot-orbit.service';
import { MCutShootOrbit } from 'app/shared/model/m-cut-shoot-orbit.model';

describe('Component Tests', () => {
  describe('MCutShootOrbit Management Update Component', () => {
    let comp: MCutShootOrbitUpdateComponent;
    let fixture: ComponentFixture<MCutShootOrbitUpdateComponent>;
    let service: MCutShootOrbitService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCutShootOrbitUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MCutShootOrbitUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MCutShootOrbitUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCutShootOrbitService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCutShootOrbit(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCutShootOrbit();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});

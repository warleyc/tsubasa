/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MMasterVersionUpdateComponent } from 'app/entities/m-master-version/m-master-version-update.component';
import { MMasterVersionService } from 'app/entities/m-master-version/m-master-version.service';
import { MMasterVersion } from 'app/shared/model/m-master-version.model';

describe('Component Tests', () => {
  describe('MMasterVersion Management Update Component', () => {
    let comp: MMasterVersionUpdateComponent;
    let fixture: ComponentFixture<MMasterVersionUpdateComponent>;
    let service: MMasterVersionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MMasterVersionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MMasterVersionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MMasterVersionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MMasterVersionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MMasterVersion(123);
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
        const entity = new MMasterVersion();
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

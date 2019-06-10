/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCutUpdateComponent } from 'app/entities/m-cut/m-cut-update.component';
import { MCutService } from 'app/entities/m-cut/m-cut.service';
import { MCut } from 'app/shared/model/m-cut.model';

describe('Component Tests', () => {
  describe('MCut Management Update Component', () => {
    let comp: MCutUpdateComponent;
    let fixture: ComponentFixture<MCutUpdateComponent>;
    let service: MCutService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCutUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MCutUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MCutUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCutService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCut(123);
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
        const entity = new MCut();
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

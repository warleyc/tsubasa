/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MModelUniformUpUpdateComponent } from 'app/entities/m-model-uniform-up/m-model-uniform-up-update.component';
import { MModelUniformUpService } from 'app/entities/m-model-uniform-up/m-model-uniform-up.service';
import { MModelUniformUp } from 'app/shared/model/m-model-uniform-up.model';

describe('Component Tests', () => {
  describe('MModelUniformUp Management Update Component', () => {
    let comp: MModelUniformUpUpdateComponent;
    let fixture: ComponentFixture<MModelUniformUpUpdateComponent>;
    let service: MModelUniformUpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MModelUniformUpUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MModelUniformUpUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MModelUniformUpUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MModelUniformUpService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MModelUniformUp(123);
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
        const entity = new MModelUniformUp();
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

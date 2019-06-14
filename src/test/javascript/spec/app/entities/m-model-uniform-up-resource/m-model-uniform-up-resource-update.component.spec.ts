/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MModelUniformUpResourceUpdateComponent } from 'app/entities/m-model-uniform-up-resource/m-model-uniform-up-resource-update.component';
import { MModelUniformUpResourceService } from 'app/entities/m-model-uniform-up-resource/m-model-uniform-up-resource.service';
import { MModelUniformUpResource } from 'app/shared/model/m-model-uniform-up-resource.model';

describe('Component Tests', () => {
  describe('MModelUniformUpResource Management Update Component', () => {
    let comp: MModelUniformUpResourceUpdateComponent;
    let fixture: ComponentFixture<MModelUniformUpResourceUpdateComponent>;
    let service: MModelUniformUpResourceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MModelUniformUpResourceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MModelUniformUpResourceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MModelUniformUpResourceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MModelUniformUpResourceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MModelUniformUpResource(123);
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
        const entity = new MModelUniformUpResource();
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

/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MModelUniformBottomResourceUpdateComponent } from 'app/entities/m-model-uniform-bottom-resource/m-model-uniform-bottom-resource-update.component';
import { MModelUniformBottomResourceService } from 'app/entities/m-model-uniform-bottom-resource/m-model-uniform-bottom-resource.service';
import { MModelUniformBottomResource } from 'app/shared/model/m-model-uniform-bottom-resource.model';

describe('Component Tests', () => {
  describe('MModelUniformBottomResource Management Update Component', () => {
    let comp: MModelUniformBottomResourceUpdateComponent;
    let fixture: ComponentFixture<MModelUniformBottomResourceUpdateComponent>;
    let service: MModelUniformBottomResourceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MModelUniformBottomResourceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MModelUniformBottomResourceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MModelUniformBottomResourceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MModelUniformBottomResourceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MModelUniformBottomResource(123);
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
        const entity = new MModelUniformBottomResource();
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

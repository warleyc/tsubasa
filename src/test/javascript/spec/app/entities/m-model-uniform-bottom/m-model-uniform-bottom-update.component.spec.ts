/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MModelUniformBottomUpdateComponent } from 'app/entities/m-model-uniform-bottom/m-model-uniform-bottom-update.component';
import { MModelUniformBottomService } from 'app/entities/m-model-uniform-bottom/m-model-uniform-bottom.service';
import { MModelUniformBottom } from 'app/shared/model/m-model-uniform-bottom.model';

describe('Component Tests', () => {
  describe('MModelUniformBottom Management Update Component', () => {
    let comp: MModelUniformBottomUpdateComponent;
    let fixture: ComponentFixture<MModelUniformBottomUpdateComponent>;
    let service: MModelUniformBottomService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MModelUniformBottomUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MModelUniformBottomUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MModelUniformBottomUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MModelUniformBottomService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MModelUniformBottom(123);
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
        const entity = new MModelUniformBottom();
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

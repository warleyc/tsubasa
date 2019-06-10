/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDistributeCardParameterStepUpdateComponent } from 'app/entities/m-distribute-card-parameter-step/m-distribute-card-parameter-step-update.component';
import { MDistributeCardParameterStepService } from 'app/entities/m-distribute-card-parameter-step/m-distribute-card-parameter-step.service';
import { MDistributeCardParameterStep } from 'app/shared/model/m-distribute-card-parameter-step.model';

describe('Component Tests', () => {
  describe('MDistributeCardParameterStep Management Update Component', () => {
    let comp: MDistributeCardParameterStepUpdateComponent;
    let fixture: ComponentFixture<MDistributeCardParameterStepUpdateComponent>;
    let service: MDistributeCardParameterStepService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDistributeCardParameterStepUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MDistributeCardParameterStepUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MDistributeCardParameterStepUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MDistributeCardParameterStepService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MDistributeCardParameterStep(123);
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
        const entity = new MDistributeCardParameterStep();
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

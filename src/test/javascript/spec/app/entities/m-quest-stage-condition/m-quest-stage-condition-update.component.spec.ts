/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestStageConditionUpdateComponent } from 'app/entities/m-quest-stage-condition/m-quest-stage-condition-update.component';
import { MQuestStageConditionService } from 'app/entities/m-quest-stage-condition/m-quest-stage-condition.service';
import { MQuestStageCondition } from 'app/shared/model/m-quest-stage-condition.model';

describe('Component Tests', () => {
  describe('MQuestStageCondition Management Update Component', () => {
    let comp: MQuestStageConditionUpdateComponent;
    let fixture: ComponentFixture<MQuestStageConditionUpdateComponent>;
    let service: MQuestStageConditionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestStageConditionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MQuestStageConditionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MQuestStageConditionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestStageConditionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MQuestStageCondition(123);
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
        const entity = new MQuestStageCondition();
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

/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestCoopRewardUpdateComponent } from 'app/entities/m-quest-coop-reward/m-quest-coop-reward-update.component';
import { MQuestCoopRewardService } from 'app/entities/m-quest-coop-reward/m-quest-coop-reward.service';
import { MQuestCoopReward } from 'app/shared/model/m-quest-coop-reward.model';

describe('Component Tests', () => {
  describe('MQuestCoopReward Management Update Component', () => {
    let comp: MQuestCoopRewardUpdateComponent;
    let fixture: ComponentFixture<MQuestCoopRewardUpdateComponent>;
    let service: MQuestCoopRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestCoopRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MQuestCoopRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MQuestCoopRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestCoopRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MQuestCoopReward(123);
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
        const entity = new MQuestCoopReward();
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

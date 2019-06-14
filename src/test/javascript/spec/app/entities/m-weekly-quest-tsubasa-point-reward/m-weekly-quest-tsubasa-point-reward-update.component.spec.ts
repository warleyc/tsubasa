/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MWeeklyQuestTsubasaPointRewardUpdateComponent } from 'app/entities/m-weekly-quest-tsubasa-point-reward/m-weekly-quest-tsubasa-point-reward-update.component';
import { MWeeklyQuestTsubasaPointRewardService } from 'app/entities/m-weekly-quest-tsubasa-point-reward/m-weekly-quest-tsubasa-point-reward.service';
import { MWeeklyQuestTsubasaPointReward } from 'app/shared/model/m-weekly-quest-tsubasa-point-reward.model';

describe('Component Tests', () => {
  describe('MWeeklyQuestTsubasaPointReward Management Update Component', () => {
    let comp: MWeeklyQuestTsubasaPointRewardUpdateComponent;
    let fixture: ComponentFixture<MWeeklyQuestTsubasaPointRewardUpdateComponent>;
    let service: MWeeklyQuestTsubasaPointRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MWeeklyQuestTsubasaPointRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MWeeklyQuestTsubasaPointRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MWeeklyQuestTsubasaPointRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MWeeklyQuestTsubasaPointRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MWeeklyQuestTsubasaPointReward(123);
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
        const entity = new MWeeklyQuestTsubasaPointReward();
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

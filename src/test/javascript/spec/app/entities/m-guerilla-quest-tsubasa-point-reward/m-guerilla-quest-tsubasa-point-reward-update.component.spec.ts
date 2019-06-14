/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuerillaQuestTsubasaPointRewardUpdateComponent } from 'app/entities/m-guerilla-quest-tsubasa-point-reward/m-guerilla-quest-tsubasa-point-reward-update.component';
import { MGuerillaQuestTsubasaPointRewardService } from 'app/entities/m-guerilla-quest-tsubasa-point-reward/m-guerilla-quest-tsubasa-point-reward.service';
import { MGuerillaQuestTsubasaPointReward } from 'app/shared/model/m-guerilla-quest-tsubasa-point-reward.model';

describe('Component Tests', () => {
  describe('MGuerillaQuestTsubasaPointReward Management Update Component', () => {
    let comp: MGuerillaQuestTsubasaPointRewardUpdateComponent;
    let fixture: ComponentFixture<MGuerillaQuestTsubasaPointRewardUpdateComponent>;
    let service: MGuerillaQuestTsubasaPointRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuerillaQuestTsubasaPointRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGuerillaQuestTsubasaPointRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGuerillaQuestTsubasaPointRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuerillaQuestTsubasaPointRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGuerillaQuestTsubasaPointReward(123);
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
        const entity = new MGuerillaQuestTsubasaPointReward();
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

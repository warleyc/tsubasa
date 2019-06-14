/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpRankingRewardGroupUpdateComponent } from 'app/entities/m-pvp-ranking-reward-group/m-pvp-ranking-reward-group-update.component';
import { MPvpRankingRewardGroupService } from 'app/entities/m-pvp-ranking-reward-group/m-pvp-ranking-reward-group.service';
import { MPvpRankingRewardGroup } from 'app/shared/model/m-pvp-ranking-reward-group.model';

describe('Component Tests', () => {
  describe('MPvpRankingRewardGroup Management Update Component', () => {
    let comp: MPvpRankingRewardGroupUpdateComponent;
    let fixture: ComponentFixture<MPvpRankingRewardGroupUpdateComponent>;
    let service: MPvpRankingRewardGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpRankingRewardGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MPvpRankingRewardGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MPvpRankingRewardGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MPvpRankingRewardGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MPvpRankingRewardGroup(123);
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
        const entity = new MPvpRankingRewardGroup();
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

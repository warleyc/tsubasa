/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLeagueRankingRewardGroupUpdateComponent } from 'app/entities/m-league-ranking-reward-group/m-league-ranking-reward-group-update.component';
import { MLeagueRankingRewardGroupService } from 'app/entities/m-league-ranking-reward-group/m-league-ranking-reward-group.service';
import { MLeagueRankingRewardGroup } from 'app/shared/model/m-league-ranking-reward-group.model';

describe('Component Tests', () => {
  describe('MLeagueRankingRewardGroup Management Update Component', () => {
    let comp: MLeagueRankingRewardGroupUpdateComponent;
    let fixture: ComponentFixture<MLeagueRankingRewardGroupUpdateComponent>;
    let service: MLeagueRankingRewardGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLeagueRankingRewardGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MLeagueRankingRewardGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MLeagueRankingRewardGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MLeagueRankingRewardGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MLeagueRankingRewardGroup(123);
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
        const entity = new MLeagueRankingRewardGroup();
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

/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTimeattackRankingRewardGroupUpdateComponent } from 'app/entities/m-timeattack-ranking-reward-group/m-timeattack-ranking-reward-group-update.component';
import { MTimeattackRankingRewardGroupService } from 'app/entities/m-timeattack-ranking-reward-group/m-timeattack-ranking-reward-group.service';
import { MTimeattackRankingRewardGroup } from 'app/shared/model/m-timeattack-ranking-reward-group.model';

describe('Component Tests', () => {
  describe('MTimeattackRankingRewardGroup Management Update Component', () => {
    let comp: MTimeattackRankingRewardGroupUpdateComponent;
    let fixture: ComponentFixture<MTimeattackRankingRewardGroupUpdateComponent>;
    let service: MTimeattackRankingRewardGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTimeattackRankingRewardGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTimeattackRankingRewardGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTimeattackRankingRewardGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTimeattackRankingRewardGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTimeattackRankingRewardGroup(123);
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
        const entity = new MTimeattackRankingRewardGroup();
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

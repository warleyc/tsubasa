/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildRoulettePrizeUpdateComponent } from 'app/entities/m-guild-roulette-prize/m-guild-roulette-prize-update.component';
import { MGuildRoulettePrizeService } from 'app/entities/m-guild-roulette-prize/m-guild-roulette-prize.service';
import { MGuildRoulettePrize } from 'app/shared/model/m-guild-roulette-prize.model';

describe('Component Tests', () => {
  describe('MGuildRoulettePrize Management Update Component', () => {
    let comp: MGuildRoulettePrizeUpdateComponent;
    let fixture: ComponentFixture<MGuildRoulettePrizeUpdateComponent>;
    let service: MGuildRoulettePrizeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildRoulettePrizeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGuildRoulettePrizeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGuildRoulettePrizeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuildRoulettePrizeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGuildRoulettePrize(123);
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
        const entity = new MGuildRoulettePrize();
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

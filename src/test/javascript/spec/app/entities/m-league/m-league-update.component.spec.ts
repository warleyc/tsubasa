/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLeagueUpdateComponent } from 'app/entities/m-league/m-league-update.component';
import { MLeagueService } from 'app/entities/m-league/m-league.service';
import { MLeague } from 'app/shared/model/m-league.model';

describe('Component Tests', () => {
  describe('MLeague Management Update Component', () => {
    let comp: MLeagueUpdateComponent;
    let fixture: ComponentFixture<MLeagueUpdateComponent>;
    let service: MLeagueService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLeagueUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MLeagueUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MLeagueUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MLeagueService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MLeague(123);
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
        const entity = new MLeague();
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

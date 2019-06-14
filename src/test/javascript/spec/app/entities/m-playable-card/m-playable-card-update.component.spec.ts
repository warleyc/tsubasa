/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPlayableCardUpdateComponent } from 'app/entities/m-playable-card/m-playable-card-update.component';
import { MPlayableCardService } from 'app/entities/m-playable-card/m-playable-card.service';
import { MPlayableCard } from 'app/shared/model/m-playable-card.model';

describe('Component Tests', () => {
  describe('MPlayableCard Management Update Component', () => {
    let comp: MPlayableCardUpdateComponent;
    let fixture: ComponentFixture<MPlayableCardUpdateComponent>;
    let service: MPlayableCardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPlayableCardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MPlayableCardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MPlayableCardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MPlayableCardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MPlayableCard(123);
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
        const entity = new MPlayableCard();
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

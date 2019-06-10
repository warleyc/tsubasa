/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionBallUpdateComponent } from 'app/entities/m-gacha-rendition-ball/m-gacha-rendition-ball-update.component';
import { MGachaRenditionBallService } from 'app/entities/m-gacha-rendition-ball/m-gacha-rendition-ball.service';
import { MGachaRenditionBall } from 'app/shared/model/m-gacha-rendition-ball.model';

describe('Component Tests', () => {
  describe('MGachaRenditionBall Management Update Component', () => {
    let comp: MGachaRenditionBallUpdateComponent;
    let fixture: ComponentFixture<MGachaRenditionBallUpdateComponent>;
    let service: MGachaRenditionBallService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionBallUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGachaRenditionBallUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGachaRenditionBallUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGachaRenditionBallService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGachaRenditionBall(123);
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
        const entity = new MGachaRenditionBall();
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

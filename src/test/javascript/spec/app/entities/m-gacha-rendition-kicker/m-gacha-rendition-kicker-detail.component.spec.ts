/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionKickerDetailComponent } from 'app/entities/m-gacha-rendition-kicker/m-gacha-rendition-kicker-detail.component';
import { MGachaRenditionKicker } from 'app/shared/model/m-gacha-rendition-kicker.model';

describe('Component Tests', () => {
  describe('MGachaRenditionKicker Management Detail Component', () => {
    let comp: MGachaRenditionKickerDetailComponent;
    let fixture: ComponentFixture<MGachaRenditionKickerDetailComponent>;
    const route = ({ data: of({ mGachaRenditionKicker: new MGachaRenditionKicker(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionKickerDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGachaRenditionKickerDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionKickerDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGachaRenditionKicker).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
